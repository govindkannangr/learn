package com.gk.beans;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProducerResponse {
	private String topic;
	private String partition;
	private String offset;
	private String timestamp;
	private String errorMessage;

}
