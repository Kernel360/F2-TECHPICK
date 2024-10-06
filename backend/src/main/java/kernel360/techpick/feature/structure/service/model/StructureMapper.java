package kernel360.techpick.feature.structure.service.model;

import org.springframework.stereotype.Component;

import kernel360.techpick.feature.structure.service.Structure;
import kernel360.techpick.feature.structure.service.node.client.ClientNode;
import kernel360.techpick.feature.structure.service.node.server.ServerNode;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StructureMapper {

    private final NameProvider nameProvider;

    public Structure<ClientNode> toClientStructure(Structure<ServerNode> serverStructure) {
        return new Structure<>(

            serverStructure.getRootFolder()
                           .stream()
                           .map(node -> node.toClientNode(nameProvider))
                           .toList(),

            serverStructure.getRecycleBinFolder()
                           .stream()
                           .map(node -> node.toClientNode(nameProvider))
                           .toList()
        );
    };
}
