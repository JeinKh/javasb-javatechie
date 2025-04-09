package com.javasb.springboot_mongo.service;

import com.javasb.springboot_mongo.model.Task;
import com.javasb.springboot_mongo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private TaskRepository repository;

    //CREATE
    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }


    //READ

    public List<Task> findAllTasks(){
        return repository.findAll();
    }

    public Task getTaskByTaskId(String taskId){
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity){
        return repository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee){
        return repository.getTaskByAssignee(assignee);
    }

    //UPDATE

    public Task updateTask(Task taskRequest){

        //get the existing document from DB
        //populate new value from request to existing object/entity/document

        Task existingTask = repository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());

        return repository.save(existingTask);
    }

    //DELETE

    public String deleteTask(String taskId){
        repository.deleteById(taskId);
        return taskId+" Task deleted from dashboard";
    }
}
