package cn.com.isurpass.house.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("installer")
public class InstallerController {

	@RequestMapping("addInstallerPage")
	public String addInstallerPage() {
		return "installer/addInstaller";
	}
	
	@RequestMapping("installerList")
	public String supplierList() {
		return "installer/installerList";
	}
}
