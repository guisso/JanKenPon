/*
 * The MIT License
 *
 * Copyright 2025 Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;.
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

import javax.swing.Icon;

/**
 * All results for a match
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.1
 * @since 0.1, Jul 19
 */
public enum Result {

    NONE(null, 0),
    // Player 1 Scissors X Paper
    P1_SCISSORS_CUT_PAPER_ONCE("/p1_scissors_cut_paper.svg", 0),
    P1_SCISSORS_CUT_PAPER_TWICE("/p1_scissors_cut_paper.svg", 1),
    P1_SCISSORS_CUT_PAPER_THREE_OR_MORE_TIMES("/p1_scissors_cut_paper.svg", 2),
    // Player 1 Rock X Scissors
    P1_ROCK_CRUSHES_SCISSORS_ONCE("/p1_rock_crushes_scissors.svg", 0),
    P1_ROCK_CRUSHES_SCISSORS_TWICE("/p1_rock_crushes_scissors.svg", 1),
    P1_ROCK_CRUSHES_SCISSORS_THREE_OR_MORE_TIMES("/p1_rock_crushes_scissors.svg", 2),
    // Player 1 Paper X Rock
    P1_PAPER_WRAPS_ROCK_ONCE("/p1_paper_wraps_rock.svg", 0),
    P1_PAPER_WRAPS_ROCK_TWICE("/p1_paper_wraps_rock.svg", 1),
    P1_PAPER_WRAPS_ROCK_THREE_OR_MORE_TIMES("/p1_paper_wraps_rock.svg", 2),
    // Player 2 Sicissor X Paper
    P2_SCISSORS_CUT_PAPER_ONCE("/p2_scissors_cut_paper.svg", 0),
    P2_SCISSORS_CUT_PAPER_TWICE("/p2_scissors_cut_paper.svg", 1),
    P2_SCISSORS_CUT_PAPER_THREE_OR_MORE_TIMES("/p2_scissors_cut_paper.svg", 2),
    // Player 2 Rock X Scissors
    P2_ROCK_CRUSHES_SCISSORS_ONCE("/p2_rock_crushes_scissors.svg", 0),
    P2_ROCK_CRUSHES_SCISSORS_TWICE("/p2_rock_crushes_scissors.svg", 1),
    P2_ROCK_CRUSHES_SCISSORS_THREE_OR_MORE_TIMES("/p2_rock_crushes_scissors.svg", 2),
    // Player 2 Paper X Rock
    P2_PAPER_WRAPS_ROCK_ONCE("/p2_paper_wraps_rock.svg", 0),
    P2_PAPER_WRAPS_ROCK_TWICE("/p2_paper_wraps_rock.svg", 1),
    P2_PAPER_WRAPS_ROCK_THREE_OR_MORE_TIMES("/p2_paper_wraps_rock.svg", 2),
    // Ties
    SCISSORS_SCISSORS_TIE("/scissors_scissors_tie.svg", 0),
    ROCK_ROCK_TIE("/rock_rock_tie.svg", 0),
    PAPER_PAPER_TIE("/paper_paper_tie.svg", 0);

    public final String IMAGE_PATH;
    public final Icon IMAGE;

    Result(String imagePath, int consecutiveVictories) {
        IMAGE_PATH = imagePath;
        IMAGE = Util.loadImage(imagePath, 32f, consecutiveVictories);
    }
}
