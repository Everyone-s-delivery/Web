package everyone.delivery.demo.domain.img.service;

import everyone.delivery.demo.common.configuration.ImgConfiguration;
import everyone.delivery.demo.common.exception.LogicalRuntimeException;
import everyone.delivery.demo.common.exception.error.CommonError;
import everyone.delivery.demo.common.exception.error.FileError;
import everyone.delivery.demo.domain.img.enums.ImageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.AbstractMap;
import java.util.Locale;

/***
 * 원본 파일 처리를 위한 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ImgService {

    private final ImgConfiguration imgConfiguration;

    private int[] getThumbnailSpace(int originalWidth, int originalHeight){
        if(originalWidth < originalHeight){
            double diff = (double)(originalHeight - 256) * ((double)originalWidth / (double)originalHeight);
            return new int[]{originalWidth - (int)diff,256};
        }else if(originalWidth > originalHeight){
            double diff = (double)(originalWidth - 256) * ((double)originalHeight / (double)originalWidth);
            return new int[]{256 ,originalHeight - (int)diff};
        }else
            return new int[]{256,256};
    }

    /***
     *
     * @param inputStream       변경을 원하는 이미지 inputStream
     * @return
     * @throws IOException
     */
    public BufferedImage resizeToThumbnailImg(InputStream inputStream)
            throws IOException {
        BufferedImage inputImage = ImageIO.read(inputStream);
        int originalWidth = inputImage.getWidth();
        int originalHeight = inputImage.getHeight();
        if(originalWidth <= 256 && originalHeight <= 256){
            return inputImage;
        }

        int[] space = getThumbnailSpace(originalWidth, originalHeight);
        int width = space[0];
        int height = space[1];
        Image resizeImage = inputImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(width, height, inputImage.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(resizeImage, 0, 0, width, height, null);
        graphics2D.dispose();

        return outputImage;
    }

    /***
     *
     * @param inputStream       저장할 파일 스트림
     * @param serverFileName    저장할 파일 이름
     * @param saveDirLocation   저장할 파일 경로
     * @return
     * @throws IOException
     */
    public String saveImg(InputStream inputStream, String serverFileName, String saveDirLocation) throws IOException {
        Path fileDirectoryPath = Paths.get(saveDirLocation).toAbsolutePath().normalize();
        if(Files.notExists(fileDirectoryPath))
            Files.createDirectories(fileDirectoryPath);
        Path filePath = fileDirectoryPath.resolve(serverFileName).normalize();

        //file uuid가 고유하기 때문에 사실상 덮어쓸 일이 없음(파일은 수정의 개념이 없고 추가 삭제에 대한 개념만 있음)
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        return serverFileName;
    }

    /***
     *
     * @param bufferedImage     저장할 파일 스트림
     * @param serverFileName    저장할 파일 이름
     * @param saveDirLocation   저장할 파일 경로
     * @return
     * @throws IOException
     */
    public String saveImg(BufferedImage bufferedImage, String serverFileName, String saveDirLocation) throws IOException {
        Path fileDirectoryPath = Paths.get(saveDirLocation).toAbsolutePath().normalize();
        if(Files.notExists(fileDirectoryPath))
            Files.createDirectories(fileDirectoryPath);
        Path filePath = fileDirectoryPath.resolve(serverFileName).normalize();

        ImageIO.write(bufferedImage, FilenameUtils.getExtension(serverFileName), filePath.toFile());
        return serverFileName;
    }

    /***
     *
     * @param serverFileName    다운로드 할 서버 파일 이름
     * @param imgType           이미지 타입
     * @return
     */
    public AbstractMap.SimpleEntry<Resource, String> getImg(String serverFileName, ImageType imgType){
        String fileExtension = FilenameUtils.getExtension(serverFileName);
        if(!isImg(fileExtension)){
            log.error("It's not an image file. fileExtension: {}", fileExtension);
            throw new LogicalRuntimeException(FileError.NOT_IMAGE_EXTENSION);
        }
        String saveDirLocation = imgConfiguration.getPath();
        if(imgType.isThumbnail())
            saveDirLocation += "/thumbnail";

        Path filePath = Paths.get(saveDirLocation).toAbsolutePath().
                resolve(serverFileName).normalize();

        if(!Files.exists(filePath)){    // 없는 파일
            log.error("file does not exist! filePath: {}", filePath.toString());
            throw new LogicalRuntimeException(FileError.NOT_EXIST_FILE);
        }
        try{
            Resource resource = new UrlResource(filePath.toUri());
            String contentType = Files.probeContentType(Paths.get(resource.getFile().getAbsolutePath()));
            if(contentType == null) {
                contentType = "application/octet-stream;charset=UTF-8";
            }
            return new AbstractMap.SimpleEntry<Resource, String>(resource,contentType + ";charset=UTF-8");
        }catch (IOException ex){
            log.error("file display fail!. {}",ex);
            throw new LogicalRuntimeException(CommonError.INTERNAL_SERVER_ERROR);
        }
    }




    /***
     * Content-Desposition 헤더에 들어갈 파일 이름을 브라우저 호환되도록 변경하는 메소드
     * @param fileName, agent
     * @return
     */
    public String convertFileName(String fileName, String agent) {
        try {
            String orginalFileName = URLDecoder.decode(fileName, "UTF-8");

            if(agent.contains("Trident"))	//Internet explore
                orginalFileName = URLEncoder.encode(orginalFileName, "UTF-8").replaceAll("\\+", " ");
            else if(agent.contains("Edge"))	//micro edge
                orginalFileName = URLEncoder.encode(orginalFileName, "UTF-8");
            else							//chrome
                orginalFileName = new String(orginalFileName.getBytes("UTF-8"), "ISO-8859-1");
            return orginalFileName;
        }catch(UnsupportedEncodingException ex) {
            log.error("fail to file encoding to UTF-8", ex);
            return fileName;
        }
    }

    /**
     * fileExtension이 이미지 확장자인지 확인
     * 이미지 확장자면 true
     * @param fileExtension
     * @return
     */
    public boolean isImg(String fileExtension){
        fileExtension = fileExtension.toLowerCase(Locale.getDefault());
        return fileExtension.equals("jpg") || fileExtension.equals("png") ||fileExtension.equals("jpeg") ||fileExtension.equals("gif");
    }
}