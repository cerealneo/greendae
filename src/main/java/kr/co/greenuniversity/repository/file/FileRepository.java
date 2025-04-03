package kr.co.greenuniversity.repository.file;

import kr.co.greenuniversity.entity.File.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}
