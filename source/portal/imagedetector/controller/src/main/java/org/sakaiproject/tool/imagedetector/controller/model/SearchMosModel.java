package org.sakaiproject.tool.imagedetector.controller.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchMosModel implements Serializable{
	private MultipartFile attachment;

}
