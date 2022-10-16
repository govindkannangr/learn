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
public class ProducerRequest {

	private String message;
}
