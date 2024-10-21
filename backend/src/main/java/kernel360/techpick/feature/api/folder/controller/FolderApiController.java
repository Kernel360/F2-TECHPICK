package kernel360.techpick.feature.api.folder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/folders")
public class FolderApiController implements FolderApiSpecification {
}
