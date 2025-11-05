package com.agendamento.agendamento.api.controller;

import com.agendamento.agendamento.api.mapper.PacienteMapper;
import com.agendamento.agendamento.api.reponse.PacienteResponse;
import com.agendamento.agendamento.api.request.PacienteRequest;
import com.agendamento.agendamento.domain.entity.Paciente;
import com.agendamento.agendamento.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;
    private final PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@RequestBody PacienteRequest request) {
        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.salvar(paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        List<PacienteResponse> pacienteResponses = mapper.toPacienteResponseList(pacientes);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> optPaciente = service.buscarPorId(id);
        return optPaciente.map(paciente -> ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toPacienteResponse(paciente)))
                .orElseGet(() -> ResponseEntity.notFound()
                .build());
    }
    @PutMapping
    public ResponseEntity<PacienteResponse> alterar(@RequestBody PacienteRequest request) {
        Paciente paciente =mapper.toPaciente(request);
        Paciente pacienteSalvo = service.salvar(paciente);
        PacienteResponse pacienteResponse = mapper.toPacienteResponse(pacienteSalvo);
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
