import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.example.math.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static org.mockito.Mockito.*;

class FuncTest {
    static double functionEps = 0.1;
    double eps =  1e-6;

    static CotFunc cotMock;
    static CscFunc cscMock;
    static SecFunc secMock;
    static SinFunc sinMock;
    static LnFunc lnMock;
    static LogFunc logMock;
    static TrigonometricFuncs trigonometricFuncsMock;

    static Reader cotIn;
    static Reader cscIn;
    static Reader secIn;
    static Reader sinIn;
    static Reader lnIn;
    static Reader logIn;

    @BeforeAll
    static void init() {
        cotMock = mock(CotFunc.class);
        cscMock = mock(CscFunc.class);
        secMock = mock(SecFunc.class);
        sinMock = mock(SinFunc.class);
        lnMock = mock(LnFunc.class);
        logMock = mock(LogFunc.class);
        trigonometricFuncsMock = mock(TrigonometricFuncs.class);

        try {
            cotIn = new FileReader("src/test/resources/CsvFiles/Inputs/CotIn.csv");
            cscIn = new FileReader("src/test/resources/CsvFiles/Inputs/CscIn.csv");
            secIn = new FileReader("src/test/resources/CsvFiles/Inputs/SecIn.csv");
            sinIn = new FileReader("src/test/resources/CsvFiles/Inputs/SinIn.csv");
            lnIn = new FileReader("src/test/resources/CsvFiles/Inputs/LnIn.csv");
            logIn = new FileReader("src/test/resources/CsvFiles/Inputs/LogIn.csv");

            // Mock data from CSVs
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(cotIn);
            for (CSVRecord record : records)
                when(cotMock.calculate(Double.parseDouble(record.get(0)))).thenReturn(Double.valueOf(record.get(1)));

            records = CSVFormat.DEFAULT.parse(cscIn);
            for (CSVRecord record : records)
                when(cscMock.calculate(Double.parseDouble(record.get(0)))).thenReturn(Double.valueOf(record.get(1)));

            records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records)
                when(secMock.calculate(Double.parseDouble(record.get(0)))).thenReturn(Double.valueOf(record.get(1)));

            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records)
                when(sinMock.calculate(Double.parseDouble(record.get(0)))).thenReturn(Double.valueOf(record.get(1)));

            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records)
                when(lnMock.calculate(Double.parseDouble(record.get(0)))).thenReturn(Double.valueOf(record.get(1)));


        } catch (IOException ex) {
            System.err.println("IOException");
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, 1.0, 10.0})
    void cotTest(double param) {
        CotFunc cot = new CotFunc();
        double expected = Math.cos(param) / Math.sin(param);
        Assertions.assertEquals(expected, cot.calculate(param), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, Math.PI, -Math.PI})
    void cotTestUndefined(double param) {
        Assertions.assertThrows(ArithmeticException.class, () -> CotFunc.calculate(param));
    }


    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, 1.0, 10.0})
    void cscTest(double param) {
        CscFunc csc = new CscFunc();
        Assertions.assertEquals(1 / Math.sin(param), csc.calculate(param), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, 0.0, 1.0, 10.0})
    void secTest(double param) {
        SecFunc sec = new SecFunc();
        Assertions.assertEquals(1 / Math.cos(param), sec.calculate(param), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.0, 0.0, 1.0, 10.0})
    void sinTest(double param) {
        SinFunc sin = new SinFunc();
        Assertions.assertEquals(Math.sin(param), sin.calculate(param), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 10.0})
    void lnTest(double param) {
        LnFunc ln = new LnFunc();
        Assertions.assertEquals(Math.log(param), ln.calculate(param), eps);
    }

//    @ParameterizedTest
//    @CsvFileSource(resources = "/CsvFiles/In/FuncSystemIn.csv", numLinesToSkip = 1)
//    void testFuncSystem(double value, double expected) {
//        FuncSystem funcSystem = new FuncSystem();
//        Assertions.assertEquals(expected, funcSystem.calculate(value), eps);
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/In/TrigonometricFuncsIn.csv")
    void testTrigonometricFuncs(Double value, Double expectedSin, Double expectedCot, Double expectedSec, Double expectedCsc) {
        TrigonometricFuncs trigonometricFuncs = new TrigonometricFuncs();

        Assertions.assertEquals(expectedSin, trigonometricFuncs.sin(value), eps);

        if (expectedCot == null) {
            Assertions.assertThrows(ArithmeticException.class, () -> trigonometricFuncs.cot(value));
        } else {
            Assertions.assertEquals(expectedCot, trigonometricFuncs.cot(value), eps);
        }

        if (expectedSec == null) {
            Assertions.assertThrows(ArithmeticException.class, () -> trigonometricFuncs.sec(value));
        } else {
            Assertions.assertEquals(expectedSec, trigonometricFuncs.sec(value), eps);
        }

        if (expectedCsc == null) {
            Assertions.assertThrows(ArithmeticException.class, () -> trigonometricFuncs.csc(value));
        } else {
            Assertions.assertEquals(expectedCsc, trigonometricFuncs.csc(value), eps);
        }
    }



    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/In/LogBaseFuncIn.csv")
    void testLogBaseFunc(double value, double base, double expected) {
        LogBaseFunc logBaseFunc = new LogBaseFunc();
        Assertions.assertEquals(expected, logBaseFunc.calculate(value, (int) base), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/In/LogFuncIn.csv")
    void testLogFunc(double value, double expectedLn, double expectedLog10, double expectedLog2) {
        LogFunc logFunc = new LogFunc();
        Assertions.assertEquals(expectedLn, logFunc.ln(value), eps);
        Assertions.assertEquals(expectedLog10, logFunc.log(value, 10), eps);
        Assertions.assertEquals(expectedLog2, logFunc.log(value, 2), eps);
    }

    @AfterAll
    static void tearDown() {
        try {
            cotIn.close();
            cscIn.close();
            secIn.close();
            sinIn.close();
            lnIn.close();
            logIn.close();
        } catch (IOException ex) {
            System.err.println("Error closing file readers");
        }
    }
}
