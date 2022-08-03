package com.dpwgc.worker.udp;

import com.dpwgc.worker.store.LogModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LogIn {
    private List<LogModel> logs;
}
