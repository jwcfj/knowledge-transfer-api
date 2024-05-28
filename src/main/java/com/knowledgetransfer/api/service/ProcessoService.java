package com.knowledgetransfer.api.service;

import com.knowledgetransfer.api.DTO.*;
import com.knowledgetransfer.api.model.*;
import com.knowledgetransfer.api.repository.AlternativaRepository;
import com.knowledgetransfer.api.repository.PossuiRepository;
import com.knowledgetransfer.api.repository.ProcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private AlternativaRepository alternativaRepository;

    @Autowired
    private PossuiRepository possuiRepository;
    public ProcessoCadastradoDTO cadastrar(ProcessoDTO dados){
        Processo processo = processoRepository.save(new Processo(dados));
        ProcessoCadastradoDTO processoCadastradoDTO = new ProcessoCadastradoDTO(processo.getId(),processo.getNome(), processo.getDescricao());
        return processoCadastradoDTO;
    }

    public ProcessoCadastradoDTO cadastrarv2(ProcessoDTOv2 dados){

        String descricao =
                "Ferramentas\n"+dados.ferramentas()
                +"----------kt----------"
                +"Indicadores\n"+dados.indicadores()
                +"----------kt----------"
                +"Métricas\n"+dados.metricas()
                +"----------kt----------"
                +"Stakeholders (perfil sugerido)\n"+dados.stakeholders()
                +"----------kt----------"
                +"Etapas\n"+dados.etapas();

        Processo processo = processoRepository.save(new Processo(dados));
        ProcessoCadastradoDTO processoCadastradoDTO = new ProcessoCadastradoDTO(processo.getId(),processo.getNome(), processo.getDescricao());
        return processoCadastradoDTO;
    }

    public void atualizar(AtualizacaoProcessoDTO dados) {
        Processo processo = processoRepository.getReferenceById(dados.id());
        if(dados.descricao()!=null) processo.setDescricao(dados.descricao());
        if(dados.nome()!=null) processo.setNome(dados.nome());
    }

    public Page<ListagemProcessoDTO> listar(Pageable paginacao){
        return processoRepository.findAll(paginacao).map(ListagemProcessoDTO::new);

    }

    public void deletar(Long id){
        processoRepository.deleteById(id);
    }

    public ListagemProcessoDTO encontrarProcesso(Long id){
        try{
            Processo processo = processoRepository.getReferenceById(id);
            return new ListagemProcessoDTO(processo);
        }
        catch(Exception e){
            return null;
        }
    }

    public RecorrenciaDTO encontrarIndicados(EncontrarIndicadosbyAlternativasDTO encontrarIndicadosbyAlternativasDTO){
        List<Processo> processos = new ArrayList<>();
        List<Alternativa> alternativas = new ArrayList<>();

        for (int i = 0; i< encontrarIndicadosbyAlternativasDTO.alternativas().size(); i++){
            if(encontrarIndicadosbyAlternativasDTO.alternativas().get(i).checked()) {
                alternativas.add(alternativaRepository.getReferenceById(encontrarIndicadosbyAlternativasDTO.alternativas().get(i).alternativa_id()));
            }
        }

        List<Possui> processosPossuidosSelecionados = possuiRepository.findAllByAlternativaIn(alternativas);
        List<ProcessoRecorrente> processoRecorrentes= new ArrayList<>();
        for (int i = 0; i<processosPossuidosSelecionados.size();i++){
            processoRecorrentes.add( new ProcessoRecorrente(processosPossuidosSelecionados.get(i).getProcesso().getId(),
                    processosPossuidosSelecionados.get(i).getProcesso().getNome(),processosPossuidosSelecionados.get(i).getProcesso().getDescricao(),0l)
            );
        }

        List<ProcessoRecorrente> temp = new ArrayList<ProcessoRecorrente>(processoRecorrentes.size());
        for (int i = 0; i < processoRecorrentes.size(); i++) {
            temp.add(new ProcessoRecorrente());

        }
        mergesort(processoRecorrentes,temp,0,processoRecorrentes.size()-1);

        Long recorrencia = 0l;
        Long total = 0l;
        Long processo_id_atual = -1l;
        processo_id_atual = processoRecorrentes.get(0).getId();
        List<RecorrenciaProcessoDTO> recorrenciaProcessoDTOS = new ArrayList<RecorrenciaProcessoDTO>();
        int index_processo_anterior=0;
        for (int i = 0; i < processoRecorrentes.size(); i++) {
            if (processo_id_atual == processoRecorrentes.get(i).getId()) {
                recorrencia++;
                total++;
            }
            else{
                recorrenciaProcessoDTOS.add(new RecorrenciaProcessoDTO(processoRecorrentes.get(index_processo_anterior).getNome(),processoRecorrentes.get(index_processo_anterior).getDescricao(),recorrencia));
                processo_id_atual = processoRecorrentes.get(i).getId();
                index_processo_anterior=i;
                recorrencia=1l;
                total++;

            }
        }
        recorrenciaProcessoDTOS.add(new RecorrenciaProcessoDTO(processoRecorrentes.get(index_processo_anterior).getNome(),processoRecorrentes.get(index_processo_anterior).getDescricao(),recorrencia));
        RecorrenciaDTO recorrenciaDTO = new RecorrenciaDTO(total,recorrenciaProcessoDTOS);
        return recorrenciaDTO;

    }




    void mergesort(List<ProcessoRecorrente> A, List<ProcessoRecorrente> temp, int l, int r){
        int mid = (l+r)/2; // Select midpoint
        if (l == r) return; // List has one element
        mergesort(A, temp, l, mid); // Mergesort first half
        mergesort(A, temp, mid+1, r); // Mergesort second half
        for (int i=l; i<=r; i++) // Copy subarray to temp
            temp.set(i,A.get(i));
        int i1 = l; int i2 = mid + 1;
        for (int curr=l; curr<=r; curr++) {
            if (i1 == mid+1) // Left sublist exhausted
                A.set(curr,temp.get(i2++));
                //A[curr] = temp[i2++];
            else if (i2 > r) // Right sublist exhausted
                A.set(curr,temp.get(i1++));
                //A[curr] = temp[i1++];
            else if (temp.get(i1).getId()<temp.get(i2).getId()) // Get bigger
                //else if (temp[i1]<temp[i2]) // Get smaller
                A.set(curr,temp.get(i1++));
                //A[curr] = temp[i1++];
            else A.set(curr,temp.get(i2++));
            //else A[curr] = temp[i2++];
        }
    }


}
