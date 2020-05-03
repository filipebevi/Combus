package com.example.veiculos.Abastecimento;

import java.util.List;

public interface AbastecimentoDAO {

    public boolean salvarAbastecimento(Abastecimento a, String acao);

    public boolean excluirAbastecimento(Abastecimento a);

    public List<Abastecimento> listarAbastecimento();


}


