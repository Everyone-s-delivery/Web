package everyone.delivery.demo.common.configuration;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.util.Base64;

@Configuration
@PropertySources(
		@PropertySource(value = "classpath:application.properties")
)
@Profile("dev")
public class DEVDatabaseConfig {
	@Value("${spring.datasource.jdbcUrl}")
	private String jdbcUrl;

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.jpa.database-platform}")
	private String platform;

	@Bean
	@Primary
	public DataSource customDataSource() {

		String secret = getSecret();
		JSONObject jsonObject = new JSONObject(secret);
		String userName = jsonObject.getString("username");
		String password = jsonObject.getString("password");

		return DataSourceBuilder
				.create()
				.url(jdbcUrl)
				.driverClassName(driverClassName)
				.username(userName)
				.password(password)
				.build();
	}

	/***
	 * AWS Secrets manager에서 DB 접속정보 가져오기
	 * 접근 정보는 환경변수로 등록해야 합니다.
	 * 		1. 로컬 환경 => 인텔리제이의 환경변수로 등록
	 * 	    2. 배포 환경 => github repo secrets으로 등록된 것을 workflow에서 읽어서 환경변수로 등록후 빌드 시 환경변수로 등록
	 * @return
	 */
	private String getSecret(){
		String secretName = "arn:aws:secretsmanager:ap-northeast-2:031874049859:secret:everyone-s-delivery-mysql-database-1-N0hRWJ";
		String region = "ap-northeast-2";

		// Create a Secrets Manager client
		AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
				.withRegion(region)
				.build();

		// In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
		// See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
		// We rethrow the exception by default.

		String secret, decodedBinarySecret;
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
				.withSecretId(secretName);
		GetSecretValueResult getSecretValueResult = null;

		try {
			getSecretValueResult = client.getSecretValue(getSecretValueRequest);
		} catch (DecryptionFailureException e) {
			// Secrets Manager can't decrypt the protected secret text using the provided KMS key.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (InternalServiceErrorException e) {
			// An error occurred on the server side.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (InvalidParameterException e) {
			// You provided an invalid value for a parameter.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (InvalidRequestException e) {
			// You provided a parameter value that is not valid for the current state of the resource.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		} catch (ResourceNotFoundException e) {
			// We can't find the resource that you asked for.
			// Deal with the exception here, and/or rethrow at your discretion.
			throw e;
		}

		// Decrypts secret using the associated KMS key.
		// Depending on whether the secret is a string or binary, one of these fields will be populated.
		if (getSecretValueResult.getSecretString() != null) {
			secret = getSecretValueResult.getSecretString();
			return secret;
		}
		else {
			decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
			return decodedBinarySecret;
		}
	}
}
