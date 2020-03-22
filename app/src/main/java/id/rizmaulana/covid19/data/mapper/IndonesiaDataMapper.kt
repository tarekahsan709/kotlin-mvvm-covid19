package id.rizmaulana.covid19.data.mapper

import id.rizmaulana.covid19.R
import id.rizmaulana.covid19.data.model.indonesia.IndonesiaDaily
import id.rizmaulana.covid19.ui.adapter.viewholders.PerCountryDailyGraphItem
import id.rizmaulana.covid19.ui.adapter.viewholders.PerCountryDailyItem

/**
 * rizmaulana 22/03/20.
 */

object IndonesiaDailyDataMapper {

    fun transformToPerCountryDaily(responses: List<IndonesiaDaily>?) = responses?.map { response ->
        PerCountryDailyItem(
            response.fid,
            response.jumlahKasusBaruperHari,
            response.jumlahPasienMeninggal,
            response.jumlahPasienSembuh,
            response.jumlahKasusKumulatif,
            response.tanggal,
            response.harike,
            R.string.indonesia_daily_info
        )
    }.orEmpty()

    fun transformIntoCountryDailyGraph(responses: List<IndonesiaDaily>?) = PerCountryDailyGraphItem(
        listData = transformToPerCountryDaily(responses.orEmpty())
    )
}
