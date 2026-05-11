package com.auxirem.controller;

import com.auxirem.model.SystemSetting;
import com.auxirem.repository.SystemSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
@RequiredArgsConstructor
public class SystemSettingController {
    private final SystemSettingRepository repository;

    @GetMapping
    public SystemSetting get() {
        return repository.findById("current_settings").orElse(new SystemSetting());
    }

    @PostMapping
    public SystemSetting save(@RequestBody SystemSetting setting) {
        setting.setId("current_settings");
        return repository.save(setting);
    }
}
