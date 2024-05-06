package telran.java52.confiquration;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiquration {
	@Bean
	ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
							.setFieldMatchingEnabled(true)
							.setFieldAccessLevel(AccessLevel.PRIVATE)//take root when model/dto private 
							.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
