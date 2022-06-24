package br.com.api.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@RestController
@RequestMapping("/timer")
public class ExemploTaskScheduleWithCron {

    public String cron  = "20,21,22,23 37 21 * * *";

    @Autowired
    private TaskScheduler task;

    private ScheduledFuture<?> scheduledFuture;


    @GetMapping("start")
    ResponseEntity<Void> start() {
        scheduledFuture = task.schedule(printHour(), new CronTrigger(cron));

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("stop")
    ResponseEntity<String> stop() {
        scheduledFuture.cancel(false);
        return ResponseEntity.status(HttpStatus.OK).body(LocalDateTime.now().toString());
    }

    private Runnable printHour() {
        return () -> System.out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
    }
}