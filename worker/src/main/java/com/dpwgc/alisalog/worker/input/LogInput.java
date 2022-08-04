package com.dpwgc.alisalog.worker.input;

import com.dpwgc.alisalog.worker.store.LogModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LogInput {
    private List<LogModel> logs;
}
