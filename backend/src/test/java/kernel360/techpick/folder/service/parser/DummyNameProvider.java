package kernel360.techpick.folder.service.parser;

import kernel360.techpick.feature.structure.service.model.NameProvider;

public class DummyNameProvider implements NameProvider {

    @Override
    public String findPickNameById(Long id) {
        return "DUMMY_PICK_NAME";
    }

    @Override
    public String findFolderNameById(Long id) {
        return "DUMMY_FOLDER_NAME";
    }
}
