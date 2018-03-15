package design.ray.a1_yuanze.aa6_ocp.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import design.ray.a1_yuanze.aa6_ocp.service.OcpService;

@Controller
@RequestMapping("/ocp")
public class OcpController {

    @Resource(name = "ocpService")
    // @Resource(name="ocpService2")
    private OcpService ocpService;

}
