package kernel360.techpick.feature.rss.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kernel360.techpick.core.model.rss.RssSupportingBlog;

public interface RssBlogRepository extends JpaRepository<RssSupportingBlog, Long> {

}
