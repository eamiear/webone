package com.webone.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.webone.core.util.FileUtil;

@RestController
@RequestMapping("backhandler")
public class BackendHandlerController {

	@RequestMapping(value = "/upload")
	public String upload(@RequestParam(value = "upload") MultipartFile icoUrl) {
		JSONObject res = new JSONObject();
		System.out.println(icoUrl);
		if(icoUrl==null) {res.put("ok", 0); return res.toString();}
		
		
		// save pics to local and return pics' path
		String ico_url = FileUtil.saveInputfileToLocal(icoUrl);
		System.out.println(ico_url);
		System.out.println(System.getProperty("user.dir"));
		if(ico_url != null){
			res.put("ok", 1);
		}
		return res.toString();
	}
	
	
}
