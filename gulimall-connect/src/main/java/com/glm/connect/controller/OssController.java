package com.glm.connect.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties("ossconfig")
public class OssController {

}
