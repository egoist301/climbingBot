package com.telegram.controller;

import com.telegram.controller.dto.UserDto;
import com.telegram.service.UserService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> get(@PathVariable(name = "id") Long id) {
    return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<UserDto>> getAll() {
    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto userDto) {
    UserDto savedUser = userService.save(userDto);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                .buildAndExpand(savedUser.getId()).toUri());
    return new ResponseEntity<>(savedUser, httpHeaders, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto,
                                        @PathVariable(name = "id") Long id) {
    userDto.setId(id);
    return new ResponseEntity<>(userService.update(userDto), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable(name = "id") Long id) {
    userService.delete(id);
  }
}
