package com.dpwgc.worker.input;

import com.dpwgc.worker.buffer.BufferProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class HttpApi {

    /**
     * HTTP方式写入日志
     */
    @PostMapping("/input")
    public Integer input(LogInput logInput) {
        BufferProducer.publish(logInput);
        return 0;
    }
}
