package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class RetryTest {

    @ParameterizedTest(name = "재입력 가능 횟수가 {0}회 남아있다면 재입력 가능 여부: {1}")
    @CsvSource({"1, true", "0, false"})
    void 재입력_가능_여부를_반환한다(final int count, final boolean result) {
        final Retry retry = new Retry(count);

        assertThat(retry.isRepeatable()).isEqualTo(result);
    }

    @Test
    void 재입력_가능_횟수를_1회_감소시킨다() {
        final Retry retry = new Retry(1);

        retry.decrease();

        assertThat(retry.isRepeatable()).isFalse();
    }

    @Test
    void 재입력_가능_횟수를_초기화시킨다() {
        final Retry retry = new Retry(0);

        retry.reset();

        assertThat(retry.isRepeatable()).isTrue();
    }
}