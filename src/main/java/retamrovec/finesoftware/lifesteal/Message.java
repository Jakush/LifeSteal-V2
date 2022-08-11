package retamrovec.finesoftware.lifesteal;

import org.jetbrains.annotations.NotNull;

public class Message {

    public String replace(String replace1, String replace2, String output1, String output2, String path, @NotNull LifeSteal lifesteal) {
        String format = lifesteal.getConfig().getString(path);

        return format != null ? format
                .replace(replace1, output1)
                .replace(replace2, output2) : null;
    }

}
