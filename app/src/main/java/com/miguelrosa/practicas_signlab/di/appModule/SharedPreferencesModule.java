package com.miguelrosa.practicas_signlab.di.appModule;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Singleton;
import at.favre.lib.armadillo.Armadillo;
import at.favre.lib.armadillo.PBKDF2KeyStretcher;
import dagger.Module;
import dagger.Provides;
@Module
public class SharedPreferencesModule {
    private Context context;
    public SharedPreferencesModule(Context context) {this.context = context;}

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {

        SharedPreferences preferences = Armadillo.create(context, "datos-aplicacion")
                .encryptionFingerprint(context)
                .keyStretchingFunction(new PBKDF2KeyStretcher())
                .build();
        return  preferences;

    }

}