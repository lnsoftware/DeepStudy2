package sdwvit.avatars;

import org.junit.Test;
import sdwvit.test.utils.Utils;

public class AvatarHelperTest {

    @Test
    public void shouldReturnFullAvatarCreatorClassBuilder() throws Exception {
        //given
        String[] args = {"1", "1000", "0.5", "0.5f", "20", "true"};

        //when
        AvatarCreatorClassBuilder result = AvatarHelper.getAvatarCreatorObjectBuilderFromArgs(args);

        //then
        Utils.assertTrue(this.getClass(), "Result equals expected object", result.equals(
                new AvatarCreatorClassBuilder()
                        .withBaseSize(1000)
                        .withFillFactor(0.5)
                        .withAlphaFactor(0.5f)
                        .withPixelSize(20)
                        .whichNeedsBorder(true)));
    }


}