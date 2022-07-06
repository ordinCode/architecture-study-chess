package co.architecture.persistance.jpa.chessgame;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChessGameRepository extends JpaRepository<ChessGameJpaEntity, Long> {
}
