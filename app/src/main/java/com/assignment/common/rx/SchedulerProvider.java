package com.assignment.common.rx;

import io.reactivex.Scheduler;

/**
 * Created by Neelam Saxena on 8/8/18.
 */
public interface SchedulerProvider  {
    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
