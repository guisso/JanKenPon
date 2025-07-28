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
 * All algorithms must have these methods to allow JanKenPon Manager to execute
 * the tournament.
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 * @version 0.1
 * @since 0.1, Jul 24, 2025
 */
public abstract class AbstractPlayer {

    private int totalScore;

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public int getTotaScore() {
        return totalScore;
    }

    public void setTotalScore(int points) {
        this.totalScore = points;
    }
    //</editor-fold>

    /**
     * Used to display the competitor's name
     *
     * @return Competitor's name
     */
    public abstract String getDeveloperName();

    /**
     * Action to be taken
     *
     * @param opponentPreviousMove Opponent previous move
     * @return My move
     */
    public abstract Move makeMyMove(Move opponentPreviousMove);

    public void incrementScoreBy(int points) {
        totalScore += points;
    }

    public void decrementScore() {
        totalScore--;
    }

    @Override
    public String toString() {
        return "[" + String.format("%03d", totalScore) + "] "
                + getDeveloperName();
    }

}
