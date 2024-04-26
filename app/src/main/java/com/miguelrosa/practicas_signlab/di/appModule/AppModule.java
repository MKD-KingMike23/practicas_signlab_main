package com.miguelrosa.practicas_signlab.di.appModule;

import android.content.Context;
import androidx.annotation.Nullable;
import com.miguelrosa.practicas_signlab.main.view.MainActivity;
import com.miguelrosa.practicas_signlab.main.view.MainView;
import com.miguelrosa.practicas_signlab.albunes.interactor.AlbunesInteractorImpl;
import com.miguelrosa.practicas_signlab.albunes.interactor.AlbunesInteractor;
import com.miguelrosa.practicas_signlab.login.interactor.LoginInteractorImpl;
import com.miguelrosa.practicas_signlab.login.interactor.LoginInteractor;
import com.miguelrosa.practicas_signlab.portadas.interactor.PortadasInteractorImpl;
import com.miguelrosa.practicas_signlab.portadas.interactor.PortadasInteractor;
import com.miguelrosa.practicas_signlab.posts.interactor.PostAddInteractor;
import com.miguelrosa.practicas_signlab.posts.interactor.PostAddInteractorImpl;
import com.miguelrosa.practicas_signlab.posts.interactor.PostInteractor;
import com.miguelrosa.practicas_signlab.posts.interactor.PostInteractorImpl;
import com.miguelrosa.practicas_signlab.posts.presenter.PostAddPresenter;
import com.miguelrosa.practicas_signlab.posts.presenter.PostAddPresenterImpl;
import com.miguelrosa.practicas_signlab.posts.presenter.PostPresenter;
import com.miguelrosa.practicas_signlab.posts.presenter.PostPresenterImpl;
import com.miguelrosa.practicas_signlab.posts.view.PostAdd;
import com.miguelrosa.practicas_signlab.posts.view.PostAddImpl;
import com.miguelrosa.practicas_signlab.posts.view.PostView;
import com.miguelrosa.practicas_signlab.posts.view.PostViewImpl;
import com.miguelrosa.practicas_signlab.usuarios.interactor.PerfilUsuarioInteractor;
import com.miguelrosa.practicas_signlab.usuarios.interactor.PerfilUsuarioInteractorImpl;
import com.miguelrosa.practicas_signlab.usuarios.interactor.UsuariosInteractorImpl;
import com.miguelrosa.practicas_signlab.usuarios.interactor.UsuariosInteractor;
import com.miguelrosa.practicas_signlab.albunes.presenter.AlbunesPresenter;
import com.miguelrosa.practicas_signlab.albunes.presenter.AlbunesPresenterImpl;
import com.miguelrosa.practicas_signlab.login.presenter.LoginPresenter;
import com.miguelrosa.practicas_signlab.portadas.presenter.PortadasPresenter;
import com.miguelrosa.practicas_signlab.portadas.presenter.PortadasPresenterImpl;
import com.miguelrosa.practicas_signlab.usuarios.presenter.PerfilUsuarioPresenter;
import com.miguelrosa.practicas_signlab.usuarios.presenter.PerfilUsuarioPresenterImpl;
import com.miguelrosa.practicas_signlab.usuarios.presenter.UsuariosPresenterImpl;
import com.miguelrosa.practicas_signlab.usuarios.presenter.UsuariosPresenter;
import com.miguelrosa.practicas_signlab.albunes.view.AlbunesFragmentImpl;
import com.miguelrosa.practicas_signlab.albunes.view.AlbunesFragment;
import com.miguelrosa.practicas_signlab.login.view.LoginView;
import com.miguelrosa.practicas_signlab.login.presenter.LoginPresenterImpl;
import com.miguelrosa.practicas_signlab.home.view.HomeActivity;
import com.miguelrosa.practicas_signlab.login.view.LoginActivity;
import com.miguelrosa.practicas_signlab.portadas.view.PortadasFragment;
import com.miguelrosa.practicas_signlab.portadas.view.PortadasFragmentImpl;
import com.miguelrosa.practicas_signlab.usuarios.view.PerfilUsuario;
import com.miguelrosa.practicas_signlab.usuarios.view.PerfilUsuarioImpl;
import com.miguelrosa.practicas_signlab.usuarios.view.UsuariosFragmentImpl;
import com.miguelrosa.practicas_signlab.usuarios.view.UsuariosFragment;
import dagger.Module;
import dagger.Provides;

@Module(includes = {SharedPreferencesModule.class, ConnectionModule.class})
public class AppModule {
    private AlbunesFragmentImpl albunesfragment;
    private PortadasFragmentImpl portadasfragment;
    private LoginActivity loginactivity;
    private MainActivity mainactivity;
    private HomeActivity homeactivity;
    private UsuariosFragmentImpl usuariosfragment;
    private PerfilUsuarioImpl perfilusuario;
    private PostViewImpl postView;
    private PostAddImpl postAdd;
    private Context context;


    // Un constructor por cada vista
    public AppModule(){}

    public AppModule(MainActivity mainactivity, Context context) {
        this.mainactivity = mainactivity;
        this.context = context;

    }
    public AppModule(LoginActivity loginactivity, Context context) {
        this.loginactivity = loginactivity;
        this.context = context;

    }

    public AppModule(HomeActivity homeactivity, Context context) {
        this.homeactivity = homeactivity;
        this.context = context;

    }

    public AppModule(UsuariosFragmentImpl usuariosfragment, Context context) {
        this.usuariosfragment = usuariosfragment;
        this.context = context;
    }

    public AppModule(AlbunesFragmentImpl albunesfragment, Context context) {
        this.albunesfragment = albunesfragment;
        this.context = context;
    }

    public AppModule(PortadasFragmentImpl portadasfragment, Context context) {
        this.portadasfragment = portadasfragment;
        this.context = context;
    }

    public AppModule(PerfilUsuarioImpl perfilusuario, Context context) {
        this.perfilusuario = perfilusuario;
        this.context = context;
    }

    public AppModule(PostViewImpl postView, Context context) {
        this.postView = postView;
        this.context = context;
    }

    public AppModule(PostAddImpl postAdd, Context context) {
        this.postAdd = postAdd;
        this.context = context;
    }


    // Un método de estos por cada vista
    @Nullable
    @Provides
    public LoginView loginactivity() {
        if(loginactivity != null){
            return loginactivity;
        }
        return null;
    }

    @Nullable
    @Provides
    public MainView mainactivity() {
        if(mainactivity != null){
            return mainactivity;
        }
        return null;
    }

    @Nullable
    @Provides
    public UsuariosFragment usuariosfragment() {
        if (usuariosfragment != null){
            return usuariosfragment;
        }

        return null;
    }

    @Nullable
    @Provides
    public AlbunesFragment albunesfragment() {
        if(albunesfragment!=null){
            return albunesfragment;

        }
        return null;
    }

    @Nullable
    @Provides
    public PortadasFragment portadasfragment() {
        if(portadasfragment!=null){
            return portadasfragment;

        }
        return null;
    }

    @Nullable
    @Provides
    public PerfilUsuario perfilusuario() {
        if (perfilusuario!=null){
            return perfilusuario;
        }
        return null;
    }

    @Nullable
    @Provides
    public PostView postView() {
        if (postView!=null){
            return postView;
        }
        return null;
    }

    @Nullable
    @Provides
    public PostAdd postAdd() {
        if (postAdd!=null){
            return postAdd;
        }
        return null;
    }

    @Provides
    public LoginPresenter providesLoginPresenterImpl(LoginPresenterImpl presenter) {
        return presenter;
    }

    @Provides
    public UsuariosPresenter providesUsuariosPresenterImpl(UsuariosPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public PortadasPresenter providesPortadasPresenterImpl(PortadasPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public AlbunesPresenter providesAlbunesPresenterImpl(AlbunesPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public PerfilUsuarioPresenter providesPerfilUsuarioPresenterImpl(PerfilUsuarioPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public PostPresenter providesPostPresenterImpl(PostPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public PostAddPresenter providesPostAddPresenterImpl(PostAddPresenterImpl presenter){
        return presenter;
    }

    @Provides
    public LoginInteractor providesLoginInteractorImpl(LoginInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    public UsuariosInteractor providesUsuariosInteractorImpl(UsuariosInteractorImpl interactor){
        return interactor;
    }

    @Provides
    public PortadasInteractor providesPortadasInteractorImpl(PortadasInteractorImpl interactor){
        return interactor;
    }

    @Provides
    public AlbunesInteractor providesAlbunesInteractorImpl(AlbunesInteractorImpl interactor){
        return interactor;
    }

    @Provides
    public PerfilUsuarioInteractor providesPerfilUsuarioInteractorImpl(PerfilUsuarioInteractorImpl interactor){
        return interactor;
    }

    @Provides
    public PostInteractor providesPostInteractorImpl(PostInteractorImpl interactor){
        return interactor;
    }

    @Provides
    public PostAddInteractor providesPostAddInteractorImpl(PostAddInteractorImpl interactor){
        return interactor;
    }

}
