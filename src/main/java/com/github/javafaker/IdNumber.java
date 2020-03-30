package com.github.javafaker;

import com.github.javafaker.idnumbers.EnIdNumber;
import com.github.javafaker.idnumbers.PtBRCpfIdNumber;
import com.github.javafaker.idnumbers.SvSEIdNumber;

public class IdNumber {
	private final Faker faker;

	private PtBRCpfIdNumber ptBrCpfIdNumber;

	protected IdNumber(Faker faker) {
		this.faker = faker;
		this.ptBrCpfIdNumber = new PtBRCpfIdNumber();
	}

	public String valid() {
		return faker.fakeValuesService().resolve("id_number.valid", this, faker);
	}

	public String invalid() {
		return faker.numerify(faker.fakeValuesService().resolve("id_number.invalid", this, faker));
	}

	public String ssnValid() {
		EnIdNumber enIdNumber = new EnIdNumber();
		return enIdNumber.getValidSsn(faker);
	}

	/**
	 * Specified as #{IDNumber.valid_sv_se_ssn} in sv-SE.yml
	 */
	public String validSvSeSsn() {
		SvSEIdNumber svSEIdNumber = new SvSEIdNumber();
		return svSEIdNumber.getValidSsn(faker);
	}

	/**
	 * Specified as #{IDNumber.invalid_sv_se_ssn} in sv-SE.yml
	 */
	public String invalidSvSeSsn() {
		SvSEIdNumber svSEIdNumber = new SvSEIdNumber();
		return svSEIdNumber.getInvalidSsn(faker);
	}

	/**
	 * Specified as #{IDNumber.valid_formatted_pt_br_cpf} in pt-BR.yml
	 */
	public String validFormattedPtBrCpf() {
		return ptBrCpfIdNumber.getValidFormattedCpf(faker);
	}

	/**
	 * Specified as #{IDNumber.valid_unformatted_pt_br_cpf} in pt-BR.yml
	 */
	public String validUnformattedPtBrCpf() {
		return ptBrCpfIdNumber.getValidUnformattedCpf(faker);
	}

	/**
	 * Specified as #{IDNumber.invalid_formatted_pt_br_cpf} in pt-BR.yml
	 */
	public String invalidFormattedPtBrCpf() {
		return ptBrCpfIdNumber.getInvalidFormattedCpf(faker);
	}

	/**
	 * Specified as #{IDNumber.invalid_unformatted_pt_br_cpf} in pt-BR.yml
	 */
	public String invalidUnformattedPtBrCpf() {
		return ptBrCpfIdNumber.getInvalidUnformattedCpf(faker);
	}
}
