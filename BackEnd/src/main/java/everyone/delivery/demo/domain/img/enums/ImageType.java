package everyone.delivery.demo.domain.file.enums;

/***
 * > 이미지의 종류
 *      > 썸네일
 *      > 원본
 * > 종류에 따라 서버에서 리사이징 해서 보관 후 적절히 서빙
 */
public enum ImageType {
    ORIGINAL,THUMBNAIL;


    /***
     * > THUMBNAIL 이면 true
     * @return
     */
    public boolean isThumbnail(){
        return this.equals(THUMBNAIL);
    }
}
