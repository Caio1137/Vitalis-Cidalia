package br.com.vitalis.cidalia.service;

// Padrao Template Method: o fluxo de gravacao e fixo, e cada servico especializa as validacoes.
public abstract class AbstractCrudService<T> {

    public final T salvar(T entidade) {
        validarAntesDeSalvar(entidade);
        return persistir(entidade);
    }

    protected void validarAntesDeSalvar(T entidade) {
    }

    protected abstract T persistir(T entidade);
}
