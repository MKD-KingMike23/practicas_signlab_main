package com.miguelrosa.practicas_signlab.di.appComponent;

import com.miguelrosa.practicas_signlab.main.view.MainActivity;
import com.miguelrosa.practicas_signlab.di.appModule.AppModule;
import com.miguelrosa.practicas_signlab.di.appModule.SharedPreferencesModule;
import com.miguelrosa.practicas_signlab.albunes.view.AlbunesFragmentImpl;
import com.miguelrosa.practicas_signlab.home.view.HomeActivity;
import com.miguelrosa.practicas_signlab.login.view.LoginActivity;
import com.miguelrosa.practicas_signlab.portadas.view.PortadasFragmentImpl;
import com.miguelrosa.practicas_signlab.posts.view.PostAddImpl;
import com.miguelrosa.practicas_signlab.posts.view.PostViewImpl;
import com.miguelrosa.practicas_signlab.usuarios.view.PerfilUsuarioImpl;
import com.miguelrosa.practicas_signlab.usuarios.view.UsuariosFragmentImpl;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SharedPreferencesModule.class})
public interface AppComponent {
    void inject(MainActivity mainactivity);

    void inject(LoginActivity loginactivity);

    void inject(HomeActivity homeactivity);

    void inject(AlbunesFragmentImpl albunesfragment);

    void inject(PortadasFragmentImpl portadasfragment);

    void inject(UsuariosFragmentImpl usuariosfragment);

    void inject(PerfilUsuarioImpl perfilusuario);

    void inject(PostViewImpl postView);

    void inject(PostAddImpl postAdd);
}