package com.dpwgc.worker.input;

import com.dpwgc.worker.store.LogModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LogInput {
    private List<LogModel> logs;
}
