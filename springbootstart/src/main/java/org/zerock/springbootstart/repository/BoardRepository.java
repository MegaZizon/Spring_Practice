package org.zerock.springbootstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.springbootstart.domain.Board;
import org.zerock.springbootstart.repository.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long> , BoardSearch {

    @Query(value = "select now()",nativeQuery = true)
    String getTime();
}
