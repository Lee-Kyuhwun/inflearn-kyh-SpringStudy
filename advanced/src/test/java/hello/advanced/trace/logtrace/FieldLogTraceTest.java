package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {

    FieldLogTrace trace = new FieldLogTrace();

    @Test
    void begin_end() {
        // given
        TraceStatus status = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello2");
        // when
        trace.end(status2);
        trace.end(status);

        // then

    }

}