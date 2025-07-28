/*
 * The MIT License
 *
 * Copyright 2025 Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.github.guisso.jankenpon;

/**
 * Possible moves in the rock-paper-scissors game
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 * @version 0.1
 * @since 0.1, Jul 18
 */
public enum Move {
    NONE, ROCK, PAPER, SCISSORS;

    private static int playerAConsecutiveWins;
    private static int playerBConsecutiveWins;

    static {
        resetConsecutiveWins();
    }

    public static void resetConsecutiveWins() {
        playerAConsecutiveWins = -1;
        playerBConsecutiveWins = -1;
    }

    public Result versus(Move opponentMove,
            AbstractPlayer playerA,
            AbstractPlayer playerB) {

        // Initialization
        if (this == NONE || opponentMove == NONE) {
            return Result.NONE;
        }

        // Tie
        if (this == opponentMove) {
            playerAConsecutiveWins = -1;
            playerBConsecutiveWins = -1;

            return switch (this) {
                case ROCK ->
                    Result.ROCK_ROCK_TIE;
                case PAPER ->
                    Result.PAPER_PAPER_TIE;
                case SCISSORS ->
                    Result.SCISSORS_SCISSORS_TIE;
                default ->
                    throw new AssertionError();
            };
        }

        // P1 Wins with Paper
        if (this == Move.PAPER && opponentMove == Move.ROCK) {

            computePointsToPlayerA(playerA, playerB);

            return switch (playerAConsecutiveWins) {
                case 0 ->
                    Result.P1_PAPER_WRAPS_ROCK_ONCE;
                case 1 ->
                    Result.P1_PAPER_WRAPS_ROCK_TWICE;
                default ->
                    Result.P1_PAPER_WRAPS_ROCK_THREE_OR_MORE_TIMES;
            };
        }

        // P1 Wins with Rock
        if (this == Move.ROCK && opponentMove == Move.SCISSORS) {

            computePointsToPlayerA(playerA, playerB);

            return switch (playerAConsecutiveWins) {
                case 0 ->
                    Result.P1_ROCK_CRUSHES_SCISSORS_ONCE;
                case 1 ->
                    Result.P1_ROCK_CRUSHES_SCISSORS_TWICE;
                default ->
                    Result.P1_ROCK_CRUSHES_SCISSORS_THREE_OR_MORE_TIMES;
            };
        }

        // P1 Wins with Scissors
        if (this == Move.SCISSORS && opponentMove == Move.PAPER) {

            computePointsToPlayerA(playerA, playerB);

            return switch (playerAConsecutiveWins) {
                case 0 ->
                    Result.P1_SCISSORS_CUT_PAPER_ONCE;
                case 1 ->
                    Result.P1_SCISSORS_CUT_PAPER_TWICE;
                default ->
                    Result.P1_SCISSORS_CUT_PAPER_THREE_OR_MORE_TIMES;
            };
        }

        // P2 Wins with Paper
        if (this == Move.ROCK && opponentMove == Move.PAPER) {

            computePointsToPlayerB(playerB, playerA);

            return switch (playerBConsecutiveWins) {
                case 0 ->
                    Result.P2_PAPER_WRAPS_ROCK_ONCE;
                case 1 ->
                    Result.P2_PAPER_WRAPS_ROCK_TWICE;
                default ->
                    Result.P2_PAPER_WRAPS_ROCK_THREE_OR_MORE_TIMES;
            };
        }

        // P1 Wins with Rock
        if (this == Move.SCISSORS && opponentMove == Move.ROCK) {

            computePointsToPlayerB(playerB, playerA);

            return switch (playerBConsecutiveWins) {
                case 0 ->
                    Result.P2_ROCK_CRUSHES_SCISSORS_ONCE;
                case 1 ->
                    Result.P2_ROCK_CRUSHES_SCISSORS_TWICE;
                default ->
                    Result.P2_ROCK_CRUSHES_SCISSORS_THREE_OR_MORE_TIMES;
            };
        }

        // P1 Wins with Scissors
        if (this == Move.PAPER && opponentMove == Move.SCISSORS) {

            computePointsToPlayerB(playerB, playerA);

            return switch (playerBConsecutiveWins) {
                case 0 ->
                    Result.P2_SCISSORS_CUT_PAPER_ONCE;
                case 1 ->
                    Result.P2_SCISSORS_CUT_PAPER_TWICE;
                default ->
                    Result.P2_SCISSORS_CUT_PAPER_THREE_OR_MORE_TIMES;
            };
        }

        return Result.NONE;
    }

    private static void computePointsToPlayerA(
            AbstractPlayer playerA, AbstractPlayer playerB) {
        
        playerBConsecutiveWins = -1;
        playerB.decrementScore();

        playerAConsecutiveWins
                = ++playerAConsecutiveWins > 1
                        ? 2
                        : playerAConsecutiveWins;

        playerA.incrementScoreBy(playerAConsecutiveWins + 1);
    }

    private static void computePointsToPlayerB(
            AbstractPlayer playerB, AbstractPlayer playerA) {
        
        playerAConsecutiveWins = -1;
        playerA.decrementScore();

        playerBConsecutiveWins
                = ++playerBConsecutiveWins > 1
                        ? 2
                        : playerBConsecutiveWins;

        playerB.incrementScoreBy(playerBConsecutiveWins + 1);
    }
}
