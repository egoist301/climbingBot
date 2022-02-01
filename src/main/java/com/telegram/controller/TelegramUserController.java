package com.telegram.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.telegram.controller.dto.TelegramUserDto;
import com.telegram.service.TelegramUserService;

@RestController
@RequestMapping("/users")
public class TelegramUserController {
  private final TelegramUserService telegramUserService;

  public TelegramUserController(TelegramUserService telegramUserService) {
    this.telegramUserService = telegramUserService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<TelegramUserDto> get(@PathVariable(name = "id") Long id) {
    return new ResponseEntity<>(telegramUserService.findById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<Set<TelegramUserDto>> getAll() {
    return new ResponseEntity<>(telegramUserService.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TelegramUserDto> save(@RequestBody @Valid TelegramUserDto userDto) {
    TelegramUserDto savedUser = telegramUserService.save(userDto);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedUser.getId()).toUri());
    return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TelegramUserDto> update(@RequestBody @Valid TelegramUserDto userDto,
                                                @PathVariable(name = "id") Long id) {

    userDto.setId(id);
    return new ResponseEntity<>(telegramUserService.update(userDto), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable(name = "id") Long id) {
    telegramUserService.delete(id);
  }
}
