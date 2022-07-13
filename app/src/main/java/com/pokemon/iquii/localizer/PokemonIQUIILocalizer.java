package com.pokemon.iquii.localizer;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.io.Serializable;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PokemonIQUIILocalizer {

    public static final String DEFAULT_LOCALE = "en-EN";

    public interface Delegate {

        Strings get();

        void save(Strings localizedStrings);

        String getLocale();

        void saveLocale(String locale);

    }

    public class ReplaceableKey {

        private String key;
        private @StringRes
        int resId;

        ReplaceableKey(String key, @StringRes int resId) {
            this.key = key;
            this.resId = resId;
        }
    }

    public class Strings implements Serializable {

        private long lastUpdate = 0;
        private Map<String, String> strings;

        Strings(Map<String, String> strings) {
            this.strings = strings;
            lastUpdate = System.currentTimeMillis();
        }

        long getLastUpdate() {
            return lastUpdate;
        }

        public Map<String, String> getStrings() {
            return strings;
        }

    }


    private static PokemonIQUIILocalizer instance;
    private Strings cache;
    private String locale = getDefaultLocale();
    private Delegate delegate;
    private final List<ReplaceableKey> replaceableKeys = new LinkedList<>();

    private PokemonIQUIILocalizer() {
    }

    private static String getDefaultLocale() {
        return Locale.getDefault().toLanguageTag();
    }

    public String getLocale() {
        return locale;
    }

    public static PokemonIQUIILocalizer setup(Delegate delegate) {
        if (instance == null) {
            instance = new PokemonIQUIILocalizer();
            Strings localizedStrings = delegate.get();
            if (localizedStrings != null && localizedStrings.getStrings() != null && !localizedStrings.getStrings()
                    .isEmpty()) {
                instance.cache = localizedStrings;
            }
            String locale = delegate.getLocale();
            if (locale != null && !locale.isEmpty() && !locale.equals("null")) {
                instance.locale = locale;
            } else {
                instance.locale = getDefaultLocale();
                delegate.saveLocale(instance.locale);
            }
        }
        instance.delegate = delegate;
        instance.update(instance.locale, UpdateForced.NO, null);
        return instance;
    }

    /**
     * @return an instance of the StringManager
     */
    public static PokemonIQUIILocalizer getInstance() {
        if (instance == null) {
            instance = new PokemonIQUIILocalizer();
        }
        return instance;
    }

    public void addReplacableKey(String key, @StringRes int stringResId) {
        replaceableKeys.add(new ReplaceableKey(key, stringResId));
    }

    /**
     * @param stringId, to use for search in keys bucket
     * @return the string mapped to the key, if the server has already communicated it, otherwise the default value
     */
    public static String get(@StringRes int stringId, Context context, Object... params) {
        if (context == null) return null;
        PokemonIQUIILocalizer instance = getInstance();
        String key = context.getResources()
                .getResourceEntryName(stringId)
                .split("/")[0];
        if ("translator-lng".equals(instance.locale)) {
            return key;
        }

        String finalString;
        if (instance.cache != null && instance.cache.getStrings() != null && instance.cache.getStrings()
                .containsKey(key) && instance.cache.getStrings()
                .get(key) != null) {
            String format = instance.cache.getStrings()
                    .get(key);

            int bufferSize = format.length() + (params == null ? 0 : params.length * 10);
            Formatter formatter = new Formatter(new StringBuilder(bufferSize));
            String string = formatter.format(format, params).toString();
            formatter.close();
            finalString = string;
        } else {
            if (stringId == 0) {
                finalString = key;
            } else if (params == null) {
                finalString = context.getResources()
                        .getString(stringId);
            } else {
                finalString = context.getResources()
                        .getString(stringId, params);
            }
        }
        if (finalString != null) {
            for (ReplaceableKey replaceableKey : instance.replaceableKeys) {
                String replaceableKeyValue = "{" + replaceableKey.key + "}";
                if (finalString.contains(replaceableKeyValue)) {
                    finalString = finalString.replace(replaceableKeyValue, get(replaceableKey.resId, context));
                }
            }

        }
        return finalString;
    }

    /**
     * Retrieve the translation strings from the server
     *
     * @param newLocal               the desired local (like en_US, it_IT, ...)
     * @param forced                 <code>UpdateForced.YES</code> if the update is forced
     * @param onStringUpdateListener a callback listener that will be call when the string retrivation ends
     */
    public void update(String newLocal, UpdateForced forced, @Nullable OnStringUpdateListener onStringUpdateListener) {
        try {
            if (onStringUpdateListener != null) {
                onStringUpdateListener.onStringUpdated();
            }
        } catch (
                NullPointerException e) {
            if (onStringUpdateListener != null) {
                onStringUpdateListener.onStringUpdated();
            }
        }
    }

    public enum UpdateForced {
        YES,
        NO
    }

    public interface OnStringUpdateListener {
        void onStringUpdated();
    }
}
