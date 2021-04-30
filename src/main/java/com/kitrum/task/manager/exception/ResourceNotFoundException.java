package com.kitrum.task.manager.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String entity, String id) {
        super(String.format("%s by id [%s] not found", entity, id));
    }
}
