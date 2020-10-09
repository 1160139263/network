package cn.blatter.network.controller;

import cn.blatter.network.common.ServiceResponse;
import cn.blatter.network.domain.Compressor;
import cn.blatter.network.service.CompressorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tanyao
 * @Date 2020/7/30 22:46
 */
@Slf4j
@RestController
public class CompressorController {

    @Autowired
    private CompressorService compressorService;

    @RequestMapping(value = "/getCompressor", method = RequestMethod.POST)
    public ServiceResponse list(@RequestBody Compressor compressor) {
        List<Compressor> compressorList = compressorService.findAll(compressor.getProjectId());
        return ServiceResponse.createBySuccess(compressorList);
    }

    @RequestMapping(value = "/addCompressor", method = RequestMethod.POST)
    public ServiceResponse addCompressor(@RequestBody Compressor compressor) {
        long startTime =  System.currentTimeMillis();
        compressorService.addCompressor(compressor);
        long endTime =  System.currentTimeMillis();
        double usedTime = (endTime*1.0-startTime*1.0)/1000;
        log.info("生成压缩机所用时间："+usedTime);
        return ServiceResponse.createBySuccess();
    }

    @RequestMapping(value = "/findCompressorById", method = RequestMethod.POST)
    public ServiceResponse findById(@RequestBody Compressor compressor) {
        Compressor compressorList = compressorService.findById(compressor.getId());
        return ServiceResponse.createBySuccess(compressorList);
    }

    @PostMapping(value = "/deleteCompressor")
    public ServiceResponse deleteCompressor(@RequestBody Compressor compressor) {
        compressorService.deleteCompressor(compressor.getId());
        return ServiceResponse.createBySuccess();
    }

    @PostMapping(value = "/setCompressor")
    public ServiceResponse setCompressor(@RequestBody Compressor compressor) {
        compressorService.setCompressor(compressor);
        return ServiceResponse.createBySuccess();
    }
}
