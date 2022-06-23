package it.prova.gestionetratte.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.model.Airbus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirbusDTO {

	private Long id;
	@NotBlank(message = "{codice.notblank}")
	private String codice;
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{dataInizioServizio}")
	private Date dataInizioServizio;
	@NotNull(message = "{numeroPasseggeri}")
	@Min(1)
	private Integer numeroPasseggeri;
	@JsonIgnoreProperties(value = { "Airbus" })
	private Set<TrattaDTO> tratte = new HashSet<TrattaDTO>(0);

	public AirbusDTO() {
		super();
	}

	public AirbusDTO(@NotBlank(message = "{codice.notblank") String codice,
			@NotBlank(message = "{descrizione.notblank") String descrizione, @NotNull Date dataInizioServizio,
			@NotNull @Min(1) Integer numeroPasseggeri, Set<TrattaDTO> tratte) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
		this.tratte = tratte;
	}

	public AirbusDTO(Long id, @NotBlank(message = "{codice.notblank") String codice,
			@NotBlank(message = "{descrizione.notblank") String descrizione, @NotNull Date dataInizioServizio,
			@NotNull @Min(1) Integer numeroPasseggeri, Set<TrattaDTO> tratte) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
		this.tratte = tratte;
	}

	public AirbusDTO(Long id, @NotBlank(message = "{codice.notblank") String codice,
			@NotBlank(message = "{descrizione.notblank") String descrizione, @NotNull Date dataInizioServizio,
			@NotNull @Min(1) Integer numeroPasseggeri) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
	}

	public AirbusDTO(@NotBlank(message = "{codice.notblank") String codice,
			@NotBlank(message = "{descrizione.notblank") String descrizione, @NotNull Date dataInizioServizio,
			@NotNull @Min(1) Integer numeroPasseggeri) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataInizioServizio = dataInizioServizio;
		this.numeroPasseggeri = numeroPasseggeri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizioServizio() {
		return dataInizioServizio;
	}

	public void setDataInizioServizio(Date dataInizioServizio) {
		this.dataInizioServizio = dataInizioServizio;
	}

	public Integer getNumeroPasseggeri() {
		return numeroPasseggeri;
	}

	public void setNumeroPasseggeri(Integer numeroPasseggeri) {
		this.numeroPasseggeri = numeroPasseggeri;
	}

	public Set<TrattaDTO> getTratte() {
		return tratte;
	}

	public void setTratte(Set<TrattaDTO> tratte) {
		this.tratte = tratte;
	}

	public Airbus buildAirbusModel() {
		return new Airbus(this.id, this.codice, this.descrizione, this.dataInizioServizio, this.numeroPasseggeri);
	}

	public static AirbusDTO buildAirbusDTOFromModel(Airbus airbusModel, boolean includeTratte) {
		AirbusDTO result = new AirbusDTO(airbusModel.getId(), airbusModel.getCodice(), airbusModel.getDescrizione(),
				airbusModel.getDataInizioServizio(), airbusModel.getNumeroPasseggeri());

		if (includeTratte)
			result.setTratte(TrattaDTO.createTratteDTOSetFromModelSet(airbusModel.getTratte(), false));
		return result;
	}

	public static List<AirbusDTO> createAirbusDTOListFromModelList(List<Airbus> modelListInput, boolean includeTratte) {
		return modelListInput.stream().map(airbusEntity -> {
			AirbusDTO result = AirbusDTO.buildAirbusDTOFromModel(airbusEntity, includeTratte);
			if (includeTratte)
				result.setTratte(TrattaDTO.createTratteDTOSetFromModelSet(airbusEntity.getTratte(), false));
			return result;
		}).collect(Collectors.toList());
	}

}
