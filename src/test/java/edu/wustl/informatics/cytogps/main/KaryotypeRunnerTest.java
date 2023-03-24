package edu.wustl.informatics.cytogps.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.wustl.informatics.cytogps.business.FinalResult;
import org.junit.jupiter.api.Test;

public class KaryotypeRunnerTest {

    @Test
    public void testGetFinalResult01() {
        FinalResult finalResult = KaryotypeRunner.getFinalResult("44,XY,-5,-7,-13,-18,+mar1,+mar3[17]");
        assertFalse(finalResult.isContainingLexerParserError());
        assertFalse(finalResult.isContainingValidationError());
    }

    @Test
    public void testGetFinalResult02() {
        FinalResult finalResult = KaryotypeRunner.getFinalResult("44,XY,-5,-7,-13,-18,+mar1,+mr3[17]");
        assertTrue(finalResult.isContainingLexerParserError());
        assertFalse(finalResult.isContainingValidationError());
        assertTrue(finalResult.getRevisedKaryotype() == null || finalResult.getRevisedKaryotype().isEmpty());
    }

    @Test
    public void testGetFinalResult03() {
        FinalResult finalResult = KaryotypeRunner.getFinalResult("46,XX,del(5)(q1q3)[4]/54~59,XX,+1,+2,+4,del(5)(q1q3),+6,+8,+10,+11,+11,+13,+13,+19,+22,+22[cp6]");
        assertFalse(finalResult.isContainingLexerParserError());
        assertTrue(finalResult.isContainingValidationError());
    }

    @Test
    public void testGetFinalResult04() {
        FinalResult finalResult = KaryotypeRunner.getFinalResult("46,XX,del(5)(q13q33)]1]/46,XX[20]");
        assertTrue(finalResult.isContainingLexerParserError());
        assertFalse(finalResult.isContainingValidationError());
        assertEquals("46,XX,del(5)(q13q33)[1]/46,XX[20]", finalResult.getRevisedKaryotype());
    }

    @Test
    public void testGetFinalResult05() {
        FinalResult finalResult = KaryotypeRunner.getFinalResult("46,XX,del(5)(q13q12)]1]/46,XX[20]");
        assertTrue(finalResult.isContainingLexerParserError());
        assertFalse(finalResult.isContainingValidationError());
        assertEquals("46,XX,del(5)(q13q12)[1]/46,XX[20]", finalResult.getRevisedKaryotype());
    }
}
