package br.com.bank.onlybank.security;

import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.enums.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String cpf;
    private String key;
    private String name;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Client client) {
        this.id = client.getId();
        this.cpf = client.getCpf();
        this.key = client.getKey();
        this.authorities = client.getProfiles().stream().map(
                p -> new SimpleGrantedAuthority(profile.getDescription())).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return new Client(id, name, cpf, key);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return key;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Profile profile) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescription()));
    }
}
