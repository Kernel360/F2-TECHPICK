package kernel360.techpick.feature.structure.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import kernel360.techpick.core.model.folder.Folder;
import kernel360.techpick.core.model.pick.Pick;
import kernel360.techpick.core.model.user.User;
import kernel360.techpick.feature.folder.service.FolderStructureService;
import kernel360.techpick.feature.pick.service.PickStructureService;

public class StructureDataProxy {

    private final Map<Long, Pick> pickMap;
    private final Map<Long, Folder> folderMap;

    private StructureDataProxy(Map<Long, Pick> pickMap, Map<Long, Folder> folderMap) {
        this.pickMap = pickMap;
        this.folderMap = folderMap;
    }

    private StructureDataProxy(List<Pick> pickList, List<Folder> folderList) {
        this.pickMap = pickList.stream().collect(Collectors.toMap(Pick::getId, Function.identity()));
        this.folderMap = folderList.stream().collect(Collectors.toMap(Folder::getId, Function.identity()));
    }

    public static StructureDataProxy fromStructureService(
        User user,
        PickStructureService pickStructureService,
        FolderStructureService folderStructureService
    ) {
        return new StructureDataProxy(
            pickStructureService.getPickListByUser(user),
            folderStructureService.getFolderListByUserAndParentFolderIsNotNull(user)
        );
    }

    public Pick findPickById(Long pickId) {
        return this.pickMap.get(pickId);
    }

    public Folder findFolderById(Long folderId) {
        return this.folderMap.get(folderId);
    }

    public boolean containsPick(Long pickId) {
        return this.pickMap.containsKey(pickId);
    }

    public boolean containsFolder(Long folderId) {
        return this.folderMap.containsKey(folderId);
    }

    public int pickListSize() {
        return this.pickMap.size();
    }

    public int folderListSize() {
        return this.folderMap.size();
    }
}
