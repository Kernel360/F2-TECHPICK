package kernel360.techpick.feature.structure.service.node.common;

import java.util.List;

import kernel360.techpick.feature.structure.service.node.server.RelationalNode;

public interface Node {

    List<RelationalNode> toNodeList(Long parentFolderId);
}
