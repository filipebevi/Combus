package com.example.veiculos.abastecimento;

import java.util.List;

public interface AbastecimentoDAO {

    public boolean salvarAbastecimento(Abastecimento a);

    public boolean excluirAbastecimento(Abastecimento a);

    public List<Abastecimento> listarAbastecimento();


}


