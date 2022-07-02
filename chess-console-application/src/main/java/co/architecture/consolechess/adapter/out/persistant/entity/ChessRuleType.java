package co.architecture.consolechess.adapter.out.persistant.entity;

import co.architecture.chess.rule.ChessRule;
import co.architecture.chess.rule.defaultrule.DefaultChessRule;

import java.util.Arrays;

public enum ChessRuleType {
    DEFAULT(DefaultChessRule.class, new DefaultChessRule());

    private final Class<? extends ChessRule> chessRuleClass;
    private final ChessRule chessRule;

    ChessRuleType(Class<? extends ChessRule> chessRuleClass, ChessRule chessRule) {
        this.chessRuleClass = chessRuleClass;
        this.chessRule = chessRule;
    }

    public static ChessRuleType ofClass(Class<? extends ChessRule> chessRuleClass) {
        return Arrays.stream(ChessRuleType.values())
                .filter(chessRuleType -> chessRuleType.chessRuleClass.equals(chessRuleClass))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public ChessRule getChessRule() {
        return chessRule;
    }
}
