package com.dpwgc.alisalog.worker.input;

import com.dpwgc.alisalog.common.model.LogBatch;
import com.dpwgc.alisalog.worker.buffer.BufferProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class HttpApi {

    /**
     * HTTP方式写入日志
     */
    @PostMapping("/input")
    public Integer input(@RequestBody LogBatch logBatch) {
        BufferProducer.publish(logBatch);
        return 0;
    }
}
