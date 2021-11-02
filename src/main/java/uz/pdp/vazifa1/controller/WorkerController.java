package uz.pdp.vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa1.common.ApiResponse;
import uz.pdp.vazifa1.entity.Department;
import uz.pdp.vazifa1.entity.Worker;
import uz.pdp.vazifa1.payload.DepartmentDto;
import uz.pdp.vazifa1.payload.WorkerDto;
import uz.pdp.vazifa1.service.DepartmentService;
import uz.pdp.vazifa1.service.WorkerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {


    @Autowired
    WorkerService workerService;

    @GetMapping
    public HttpEntity<List<Worker>> getWorkers() {
        List<Worker> workers = workerService.getWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Integer id) {
        Worker worker = workerService.getWorker(id);
        return ResponseEntity.ok(worker);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addWorker(@Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editWorker(@PathVariable Integer id, @Valid @RequestBody WorkerDto workerDto) {
        ApiResponse apiResponse = workerService.editWorker(id, workerDto);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
        if (apiResponse.isSuccess())
            return ResponseEntity.status(201).body(apiResponse);

        return ResponseEntity.status(409).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
